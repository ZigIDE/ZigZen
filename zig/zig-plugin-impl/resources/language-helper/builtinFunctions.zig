// The list of builtin functions of current Zig master.

/// Returns the absolute value of an integer or a floating point number.
fn abs(value: anytype) @TypeOf(value) {}

/// Converts a pointer from one address space to another. The new address space is inferred based on the result type.
fn addrSpaceCast(ptr: anytype) @TypeOf(ptr) {}

/// Performs `a + b` and returns a tuple with the result and a possible overflow bit.
fn addWithOverflow(a: anytype, b: anytype) struct { @TypeOf(a, b), u1 } {}

/// Changes the alignment of a pointer. The alignment to use is inferred based on the result type.
fn alignCast(ptr: anytype) @TypeOf(ptr) {}

/// Returns the number of bytes that this type should be aligned to for the current target to match the C ABI.
fn alignOf(comptime T: type) comptime_int {}

/// Performs [Type Coercion](https://ziglang.org/documentation/master/#Type-Coercion).
fn as(comptime T: type, expression) T {}

/// Dereferences atomically a pointer to a `T` and returns the value.
fn atomicLoad(comptime T: type, ptr: *const T, comptime ordering: AtomicOrder) T {}

/// Dereferences a pointer to a `T` and atomically modifies the value and returns the previous value.
fn atomicRmw(comptime T: type, ptr: *const T, comptime ordering: AtomicOrder) T {}

/// Dereferences a pointer to a `T` and atomically stores the given value.
fn atomicStore(comptime T: type, ptr: *T, value: T, comptime ordering: AtomicOrder) void {}

/// Converts a value of one type to another type. The return type is the inferred result type.
fn bitCast(value: anytype) @TypeOf(value) {}

/// Returns the bit offset of a field relative to its containing struct.
fn bitOffsetOf(comptime T: type, comptime field_name: []const u8) comptime_int {}

/// Reverses the bit pattern of an integer value, including the sign bit if applicable.
fn bitReverse(integer: anytype) T {}

/// Returns the number of bits it takes to store `T` in memory if the type were a field in a packed struct or union.
fn bitSizeOf(comptime T: type) comptime_int {}

/// This function inserts a platform-specific debug trap instruction which causes debuggers to break there.
fn breakpoint() void {}

/// Swaps the byte order of the integer. This converts a big endian integer to a little endian integer, and converts
/// a little endian integer to a big endian integer.
fn byteSwap(operand: anytype) T {}

// TODO: figure out proper return type
/// Calls a function, in the same way that invoking an expression with parentheses does.
fn call(modifier: CallModifier, function: anytype, args: anytype) T {}

/// Returns the smallest integral value not less than the given floating point number.
fn ceil(value: anytype) @TypeOf(value) {}

/// Appends `#define $name $value` to the `@cImport` temporary buffer.
fn cDefine(comptime name: []const u8, value) void {}

/// Parses C code and imports the functions, types, variables, and compatible macro definitions into a new empty struct
/// type, and then returns that type.
fn cImport(expression) type {}

/// Appends `#include <$path>\n` to the `@cImport` temporary buffer.
fn cInclude(comptime path: []const u8) void {}

/// Counts the number of most-significant (leading in a big-endian sense) zeroes in an integer - "count leading zeroes".
fn clz(operand: anytype) @TypeOf(operand) {}

/// Performs a strong atomic compare-and-exchange operation, returning `null` if the current value is not the given
/// expected value.
fn cmpxchgStrong(comptime T: type, ptr: *T, expected_value: T, new_value: T, success_order: AtomicOrder, fail_order: AtomicOrder) ?T {}

/// Performs a weak atomic compare-and-exchange operation, returning `null` if the current value is not the given
/// expected value.
fn cmpxchgWeak(comptime T: type, ptr: *T, expected_value: T, new_value: T, success_order: AtomicOrder, fail_order: AtomicOrder) ?T {}

/// Causes a compile error with the message `msg` when semantically checked.
fn compileError(comptime msg: []const u8) noreturn {}

// TODO: figure out proper `args` parameter type
/// Prints the arguments passed to it at compile-time.
fn compileLog(args: VaList) void {}

/// Removes `const` qualifier from a pointer.
fn constCast(value: anytype) DestType {}

/// Computes the cosine trigonometric function on a floating point number in radians. Uses a dedicated hardware
/// instruction when available.
fn cos(value: anytype) @TypeOf(value) {}

/// Counts the number of least-significant (trailing in a big-endian sense) zeroes in an integer - "count trailing
/// zeroes".
fn ctz(operand: anytype) @TypeOf(operand) {}

/// Appends `#undef $name` to the `@cImport` temporary buffer.
fn cUndef(comptime name: []const u8) void {}

/// Implements the C macro `va_arg`.
fn cVaArg(operand: *VaList, comptime T: type) T {}

/// Implements the C macro `va_copy`.
fn cVaCopy(src: *VaList) VaList {}

/// Implements the C macro `va_end`.
fn cVaEnd(src: *VaList) void {}

/// Implements the C macro `va_start`. Only valid inside a variadic function.
fn cVaStart() VaList {}

/// Performs exact division.
fn divExact(numerator: T, denominator: T) T {}

/// Performs floored division.
fn divFloor(numerator: T, denominator: T) T {}

/// Performs truncated division.
fn divTrunc(numerator: T, denominator: T) T {}

/// Returns a compile time constant pointer to null-terminated, fixed-size array with length equal to the byte count of
/// the file given by `path`.
fn embedFile(comptime path: []const u8) *const [N:0]u8 {}

// TODO: figure out proper return type
/// Converts an integer into an [enum](https://ziglang.org/documentation/master/#enum) value.
fn enumFromInt(integer: anytype) T {}

// TODO: figure out proper return type
/// Converts an error set or error union value from one error set to another error set.
fn errorCast(value: anytype) T {}

/// Converts from the integer representation of an error into [The Global Error Set](https://ziglang.org/documentation/master/#The-Global-Error-Set) type.
fn errorFromInt(value: Int(.unsigned, @bitSizeOf(anyerror))) anyerror {}

/// Returns the string representation of an error. The string representation of `error.OutOfMem` is `"OutOfMem"`.
fn errorName(err: anyerror) [:0]const u8 {}

/// Returns a stack trace object if the binary is built with error return tracing, and this function is invoked in a function that calls
/// a function with an error or error union return type; otherwise returns `null`.
fn errorReturnTrace() ?*StackTrace {}

/// Computes the base-e exponential function on a floating point number.
fn exp(value: anytype) @TypeOf(value) {}

/// Computes the base-2 exponential function on a floating point number.
fn exp2(value: anytype) @TypeOf(value) {}

/// Creates a symbol in the output object file.
fn export_(declaration, comptime options: ExportOptions) void {}

/// Creates a reference to an external symbol in the output object file.
fn extern_(T: type, comptime options: ExternOptions) T {}

/// Introduces happens-before edges between operations.
fn fence(order: AtomicOrder) void {}

// TODO: figure out proper return type
/// Performs field access by a compile-time string. Works on both fields and declarations.
fn field(lhs: anytype, comptime field_name: []const u8) T {}

/// Returns the base pointer of a struct given a pointer to a field in the struct.
fn fieldParentPtr(comptime ParentType: type, comptime field_name: []const u8, field_ptr: *T) *ParentType {}

// TODO: figure out proper return type
/// Convert from one float type to another. This cast is safe, but may cause the numeric value to lose precision. The return type is the
/// inferred result type.
fn floatCast(value: anytype) T {}

// TODO: figure out proper return type
/// Converts an integer to the closest floating point representation. The return type is the inferred result type.
fn floatFromInt(int: anytype) T {}

/// Returns the largest integral value not greater than the given floating point number. Uses a dedicated hardware instruction when available.
fn floor(value: anytype) @TypeOf(value) {}

/// Returns the base pointer of the current stack frame.
fn frameAddress() usize {}

/// Returns whether or not a [container](https://ziglang.org/documentation/master/#Containers) has a declaration matching `name`.
fn hasDecl(comptime Container: type, comptime name: []const u8) bool {}

/// Returns whether the field name of a struct, union, or enum exists.
fn hasField(comptime Container: type, comptime name: []const u8) bool {}

/// Finds a Zig file corresponding to `path` and adds it to the build, if it is not already added.
fn import(comptime path: []const u8) type {}

/// Returns whether the builtin was run in a comptime context. The result is a compile-time constant.
fn inComptime() bool {}

// TODO: figure out proper return type
/// Converts an integer to another integer while keeping the same numerical value. The return type is the inferred result type.
fn intCast(int: anytype) T {}

/// Converts `true` to `@as(u1, 1)` and `false` to `@as(u1, 0)`.
fn intFromBool(value: bool) u1 {}

// TODO: figure out proper return type
/// Converts an enumeration value into its integer tag type. When a tagged union is passed, the tag value is used as the enumeration value.
fn intFromEnum(enum_or_tagged_union: anytype) T {}

/// Converts an error to the integer representation of an error.
fn intFromError(err: anytype) Int(.unsigned, @bitSizeOf(anyerror)) {}

/// Converts value to a `usize` which is the address of the pointer. value can be `*T` or `?*T`.
fn intFromPtr(value: anytype) usize {}

/// Computes the natural logarithm of a floating point number. Uses a dedicated hardware instruction when available.
fn log(value: anytype) @TypeOf(value) {}

/// Computes the logarithm to the base 2 of a floating point number. Uses a dedicated hardware instruction when available.
fn log2(value: anytype) @TypeOf(value) {}

/// Computes the logarithm to the base 10 of a floating point number. Uses a dedicated hardware instruction when available.
fn log10(value: anytype) @TypeOf(value) {}

/// Returns the maximum value of `a` and `b`. This builtin accepts integers, floats, and vectors of either. In the latter case, the
/// operation is performed element wise.
fn max(a: T, b: T) T {}

/// Copies bytes from one region of memory to another.
fn memcpy(noalias dest, noalias source) void {}

/// Sets all the elements of a memory region to `elem`.
fn memset(dest, elem) void {}

/// Returns the minimum value of `a` and `b`. This builtin accepts integers, floats, and vectors of either. In the latter case, the operation
/// is performed element wise.
fn min(a: T, b: T) T {}

/// Performs modulus division. For unsigned integers this is the same as `numerator % denominator`. Caller guarantees `denominator > 0`,
/// otherwise the operation will result in a [Remainder Division by Zero](https://ziglang.org/documentation/master/#Remainder-Division-by-Zero)
/// when runtime safety checks are enabled.
fn mod(numerator: T, denominator: T) T {}

/// Performs fused multiply-add, similar to `(a * b) + c`, except only rounds once, and is thus more accurate.
///
/// Supports [Floats](https://ziglang.org/documentation/master/#Floats) and [Vectors](https://ziglang.org/documentation/master/#Vectors) of floats.
fn mulAdd(comptime T: type, a: T, b: T, c: T) T {}

/// Performs `a * b` and returns a tuple with the result and a possible overflow bit.
fn mulWithOverflow(a: anytype, b: anytype) struct { @TypeOf(a, b), u1 } {}

/// Returns the byte offset of a field relative to its containing struct.
///
/// See also:
/// - `@bitOffsetOf`
fn offsetOf(comptime T: type, comptime field_name: []const u8) comptime_int {}

/// Invokes the panic handler function. By default the panic handler function calls the public panic function exposed in the root source
/// file, or if there is not one specified, the `default_panic` function from `std/builtin.zig`.
fn panic(message: []const u8) noreturn {}

/// Counts the number of bits set in an integer - "population count".
fn popCount(operand: anytype) @TypeOf(operand) {}

/// Tells the compiler to emit a prefetch instruction if supported by the target CPU. If the target CPU does not support the requested
/// prefetch instruction, this builtin is a no-op. This function has no effect on the behavior of the program, only on the performance
/// characteristics.
fn prefetch(ptr: anytype, comptime options: PrefetchOptions) void {}

// TODO: figure out proper return type
/// Converts a pointer of one type to a pointer of another type. The return type is the inferred result type.
fn ptrCast(value: anytype) T {}

/// Converts an integer to a pointer. The return type is the inferred result type. Casting an address of `0` to a destination type which
/// is not optional and does not have the `allowzero` attribute will result in a [Pointer Cast Invalid Null](https://ziglang.org/documentation/master/#Pointer-Cast-Invalid-Null)
/// panic when runtime safety checks are enabled.
fn ptrFromInt(address: usize) T {}

/// Performs remainder division. For unsigned integers this is the same as `numerator % denominator`. Caller guarantees `denominator > 0`,
/// otherwise the operation will result in a [Remainder Division by Zero](https://ziglang.org/documentation/master/#Remainder-Division-by-Zero)
/// when runtime safety checks are enabled.
fn rem(numerator: T, denominator: T) T {}

/// Returns the address of the next machine code instruction that will be executed when the current function returns.
fn returnAddress() usize {}

/// Rounds the given floating point number to an integer, away from zero. Uses a dedicated hardware instruction when available.
fn round(value: anytype) @TypeOf(value) {}

/// Selects values element-wise from `a` or `b` based on `pred`. If `pred[i]` is `true`, the corresponding element in the result will be
/// `a[i]` and otherwise `b[i]`.
fn select(comptime T: type, pred: @Vector(len, bool), a: @Vector(len, T), b: @Vector(len, T)) @Vector(len, T) {}

/// Ensures that a function will have a stack alignment of at least `alignment` bytes.
fn setAlignStack(comptime alignment: u29) void {}

/// Tells the optimizer that the current function is (or is not) rarely called. This function is only valid within function scope.
fn setCold(comptime is_cold: bool) void {}

/// Increase the maximum number of backwards branches that compile-time code execution can use before giving up and making a compile error.
fn setEvalBranchQuota(comptime new_quota: u32) void {}

/// Changes the current scope's rules about how floating point operations are defined.
fn setFloatMode(comptime mode: FloatMode) void {}

/// Sets whether runtime safety checks are enabled for the scope that contains the function call.
fn setRuntimeSafety(comptime safety_on: bool) void {}

/// Performs the left shift operation (`<<`). For unsigned integers, the result is [undefined](https://ziglang.org/documentation/master/#undefined)
/// if any 1 bits are shifted out. For signed integers, the result is [undefined](https://ziglang.org/documentation/master/#undefined) if
/// any bits that disagree with the resultant sign bit are shifted out.
fn shlExact(value: T, shift_amt: Log2T) T {}

/// Performs `a << b` and returns a tuple with the result and a possible overflow bit.
fn shlWithOverflow(a: anytype, shift_amt: Log2T) struct { @TypeOf(a), u1 } {}

/// Performs the right shift operation (`>>`). Caller guarantees that the shift will not shift any 1 bits out.
fn shrExact(value: T, shift_amt: Log2T) T {}

/// Constructs a new [vector](https://ziglang.org/documentation/master/#Vectors) by selecting elements from `a` and `b` based on `mask`.
fn shuffle(comptime E: type, a: @Vector(a_len, E), b: @Vector(b_len, E), comptime mask: @Vector(mask_len, i32)) @Vector(mask_len, E) {}

/// Computes the sine trigonometric function on a floating point number in radians. Uses a dedicated hardware instruction when available.
fn sin(value: anytype) @TypeOf(value) {}

/// This function returns the number of bytes it takes to store `T` in memory. The result is a target-specific compile time constant.
fn sizeOf(comptime T: type) comptime_int {}

// TODO: figure out proper return type
/// Produces a vector where each element is the value `scalar`. The return type and thus the length of the vector is inferred.
fn splat(scalar: anytype) T {}

/// Performs the square root of a floating point number. Uses a dedicated hardware instruction when available.
fn sqrt(value: anytype) @TypeOf(value) {}

/// Returns a `SourceLocation` struct representing the function's name and location in the source code. This must be called in a function.
fn src() SourceLocation {}

/// Performs `a - b` and returns a tuple with the result and a possible overflow bit.
fn subWithOverflow(a: anytype, b: anytype) struct { @TypeOf(a, b), u1 } {}

/// Converts an enum value or union value to a string literal representing the name.
fn tagName(value: anytype) [:0]const u8 {}

/// Computes the tangent trigonometric function on a floating point number in radians. Uses a dedicated hardware instruction when available.
fn tan(value: anytype) @TypeOf(value) {}

/// Returns the innermost struct, enum, or union that this function call is inside.
fn This() type {}

/// Inserts a platform-specific trap/jam instruction which can be used to exit the program abnormally. This may be implemented by explicitly
/// emitting an invalid instruction which may cause an illegal instruction exception of some sort. Unlike for `@breakpoint()`, execution does
/// not continue after this point.
fn trap() noreturn {}

/// Rounds the given floating point number to an integer, towards zero. Uses a dedicated hardware instruction when available.
fn trunc(value: anytype) @TypeOf(value) {}

// TODO: figure out proper return type
/// This function truncates bits from an integer type, resulting in a smaller or same-sized integer type. The return type is the inferred
/// result type.
fn truncate(integer: anytype) T {}

/// Reifies type information into a type.
fn Type(comptime info: Type) type {}

/// Provides type reflection.
fn typeInfo(comptime T: type) Type {}

/// This function returns the string representation of a type, as an array. It is equivalent to a string literal of the type name. The
/// returned type name is fully qualified with the parent namespace included as part of the type name with a series of dots.
fn typeName(T: type) *const [N:0]u8 {}

/// A special builtin function that takes any (nonzero) number of expressions as parameters and returns the type of the result, using
/// [Peer Type Resolution](https://ziglang.org/documentation/master/#Peer-Type-Resolution).
fn TypeOf(...) type {}

fn unionInit(comptime Union: type, comptime active_field_name: []const u8, init_expr) Union {}

/// Creates [Vectors](https://ziglang.org/documentation/master/#Vectors).
fn Vector(len: comptime_int, Element: type) type {}

/// Removes the `volatile` qualifier from a pointer.
fn volatileCast(value: anytype) DestType {}

/// Increases the size of the Wasm memory identified by `index` by `delta` in units of unsigned number of Wasm pages. Note that each Wasm
/// page is 64KB in size. On success, returns previous memory size; on failure, if the allocation fails, returns `-1`.
fn wasmMemoryGrow(index: u32, delta: u32) i32 {}

/// Returns the size of the Wasm memory identified by `index` as an unsigned value in units of Wasm pages. Note that each Wasm page is
/// 64KB in size.
fn wasmMemorySize(index: u32) u32 {}

/// Returns the index of the work group in the current kernel invocation in dimension `dimension`.
fn workGroupId(comptime dimension: u32) u32 {}

/// Returns the number of work items that a work group has in dimension `dimension`.
fn workGroupSize(comptime dimension: u32) u32 {}

/// Returns the index of the work item in the work group in dimension `dimension`. This function returns values between `0` (inclusive)
/// and `@workGroupSize(dimension)` (exclusive).
fn workItemId(comptime dimension: u32) u32 {}
