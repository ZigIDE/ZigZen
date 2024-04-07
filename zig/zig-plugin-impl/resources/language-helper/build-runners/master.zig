const build = @import("@build");
const dependencies = @import("@dependencies");
const std = @import("std");

pub fn main() !void {
    var arena = std.heap.ArenaAllocator.init(std.heap.page_allocator);
    defer arena.deinit();

    const allocator = arena.allocator();

    const args = try std.process.argsAlloc(allocator);

    var start_index = 1;

    const zig_executable = nextArg(args, &start_index) orelse {
        std.log.warn("First argument expected to be path to the Zig compiler binary\n", .{});
        return error.InvalidArgs;
    };

    const build_root = nextArg(args, &start_index) orelse {
        std.log.warn("Second argument expected to be build root directory path\n", .{});
        return error.InvalidArgs;
    };
    const cache_root = nextArg(args, &start_index) orelse {
        std.log.warn("Third argument expected to be cache root directory path\n", .{});
        return error.InvalidArgs;
    };
    const global_cache_root = nextArg(args, &start_index) orelse {
        std.log.warn("Fourth argument expected to be global cache root directory path\n", .{});
        return error.InvalidArgs;
    };

    const build_root_directory = std.Build.Cache.Directory{
        .path = build_root,
        .handle = try std.fs.cwd().openDir(build_root, .{}),
    };

    const local_cache_directory = std.Build.Cache.Directory{
        .path = cache_root,
        .handle = try std.fs.cwd().makeOpenPath(cache_root, .{}),
    };

    const global_cache_directory = std.Build.Cache.Directory{
        .path = global_cache_root,
        .handle = try std.fs.cwd().makeOpenPath(global_cache_root, .{}),
    };

    var graph: std.Build.Graph = .{
        .arena = allocator,
        .cache = .{
            .gpa = allocator,
            .manifest_dir = try local_cache_directory.handle.makeOpenPath("h", .{}),
        },
        .zig_exe = zig_executable,
        .env_map = try std.process.getEnvMap(allocator),
        .global_cache_root = global_cache_directory,
    };

    graph.cache.addPrefix(.{ .path = null, .handle = std.fs.cwd() });
    graph.cache.addPrefix(build_root_directory);
    graph.cache.addPrefix(local_cache_directory);
    graph.cache.addPrefix(global_cache_directory);
    graph.cache.hash.addBytes(std.builtin.zig_version_string);

    const builder = try std.Build.create(
        &graph,
        build_root_directory,
        local_cache_directory,
        dependencies.root_deps,
    );

    var output_tmp_nonce: ?[16]u8 = null;

    while (nextArg(args, &start_index)) |arg| {
        if (std.mem.startsWith(u8, arg, "-Z")) {
            if (arg.len != 18) {
                std.log.err("bad argument: '{s}'", .{arg});
                return error.InvalidArgs;
            }

            output_tmp_nonce = arg[2..18].*;
        } else if (std.mem.startsWith(u8, arg, "-D")) {
            const option_contents = arg[2..];

            if (option_contents.len == 0) {
                std.log.err("Expected option name after '-D'\n\n", .{});
                return error.InvalidArgs;
            }

            if (std.mem.indexOfScalar(u8, option_contents, '=')) |name_end| {
                const option_name = option_contents[0..name_end];
                const option_value = option_contents[name_end + 1 ..];

                if (try builder.addUserInputOption(option_name, option_value)) {
                    std.log.err("Option conflict '-D{s}'\n\n", .{option_name});
                    return error.InvalidArgs;
                }
            } else {
                const option_name = option_contents;

                if (try builder.addUserInputFlag(option_name)) {
                    std.log.err("Option conflict '-D{s}'\n\n", .{option_name});
                    return error.InvalidArgs;
                }
            }
        } else if (std.mem.eql(u8, arg, "--zig-lib-dir")) {
            const zig_lib_dir = nextArg(args, &start_index) orelse {
                std.log.err("Expected argument after '{s}'", .{arg});
                return error.InvalidArgs;
            };

            builder.zig_lib_dir = .{ .cwd_relative = zig_lib_dir };
        }
    }

    const host_query = std.Build.parseTargetQuery(graph.host_query_options) catch |err| switch (err) {
        error.ParseFailed => std.process.exit(1),
    };
    builder.host = .{
        .query = .{},
        .result = try std.zig.system.resolveTargetQuery(host_query),
    };

    builder.resolveInstallPrefix(null, std.Build.DirList{});
    try runBuild(builder);

    if (graph.needed_lazy_dependencies.entries.len != 0) {
        var buffer: std.ArrayListUnmanaged(u8) = .{};

        for (graph.needed_lazy_dependencies.keys()) |k| {
            try buffer.appendSlice(allocator, k);
            try buffer.append(allocator, '\n');
        }

        const s = std.fs.path.sep_str;
        const tmp_sub_path = "tmp" ++ s ++ (output_tmp_nonce orelse std.debug.panic("missing -Z arg", .{}));

        local_cache_directory.handle.writeFile2(.{
            .sub_path = tmp_sub_path,
            .data = buffer.items,
            .flags = .{ .exclusive = true },
        }) catch |err| {
            std.debug.panic("unable to write configuration results to '{}{s}': {s}", .{
                local_cache_directory, tmp_sub_path, @errorName(err),
            });
        };

        std.process.exit(3);
    }

    // todo
}

fn nextArg(args: [][:0]const u8, index: *usize) ?[:0]const u8 {
    if (index.* >= args.len) {
        return null;
    }

    defer index.* += 1;

    return args[index.*];
}

fn runBuild(builder: *std.Build) anyerror!void {
    switch (@typeInfo(@typeInfo(@TypeOf(build.build)).Fn.return_type.?)) {
        .Void => build.build(builder),
        .ErrorUnion => try build.build(builder),
        else => @compileError("expected return type of build to be 'void' or '!void'"),
    }
}
