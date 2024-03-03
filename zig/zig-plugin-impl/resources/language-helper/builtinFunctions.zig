// The list of builtin functions of current Zig master.

/// Returns the absolute value of an integer or a floating point number. Uses a dedicated hardware instruction when
/// available. The return type is always an unsigned integer of the same bit width as the operand if the operand is
/// an integer. Unsigned integer operands are supported. The builtin cannot overflow for signed integer operands.
fn abs(value: anytype) @TypeOf(value) {}

/// Converts a pointer from one address space to another. The new address space is inferred based on the result type.
/// Depending on the current target and address spaces, this cast may be a no-op, a complex operation, or illegal. If
/// the cast is legal, then the resulting pointer points to the same memory location as the pointer operand. It is
/// always valid to cast a pointer between the same address spaces.
fn addrSpaceCast(ptr: anytype) @TypeOf(ptr) {}

/// Performs `a + b` and returns a tuple with the result and a possible overflow bit.
fn addWithOverflow(a: anytype, b: anytype) struct { @TypeOf(a, b), u1 } {}

/// Changes the alignment of a pointer. The alignment to use is inferred based on the result type.
///
/// `ptr` can be `*T`, `?*T`, or `[]T`. A [pointer alignment safety check](https://ziglang.org/documentation/master/#Incorrect-Pointer-Alignment)
/// is added to the generated code to make sure the pointer is aligned as promised.
fn alignCast(ptr: anytype) @TypeOf(ptr) {}

/// Returns the number of bytes that this type should be aligned to for the current target to match the C ABI. When
/// the child type of a pointer has this alignment, the alignment can be omitted from the type.
fn alignOf(comptime T: type) comptime_int {}

/// Performs Type Coercion. This cast is allowed when the conversion is unambiguous and safe, and is the preferred
/// way to convert between types, whenever possible.
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

/// Returns the number of bits it takes to store `T` in memory if the type were a field in a packed struct orunion. The
/// result is a target-specific compile time constant.
fn bitSizeOf(comptime T: type) comptime_int {}

/// Swaps the byte order of the integer. This converts a big endian integer to a little endian integer, and converts
/// a little endian integer to a big endian integer.
fn byteSwap(operand: anytype) T {}

// TODO: figure out proper return type
/// Calls a function, in the same way that invoking an expression with parentheses does.
fn call(modifier: CallModifier, function: anytype, args: anytype) @TypeOf(function) {}

/// Returns the smallest integral value not less than the given floating point number. Uses a dedicated hardware
/// instruction when available.
fn ceil(value: anytype) @TypeOf(value) {}

/// Parses C code and imports the functions, types, variables, and compatible macro definitions into a new empty struct
/// type, and then returns that type.
fn cImport(expression) type {}

/// Appends `#define $name $value` to the `@cImport` temporary buffer.
fn cDefine(comptime name: []const u8, value) void {}

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
fn constCast(value: anytype) DsetType {}

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

///Performs exact division. Caller guarantees `denominator != 0` and `@divTrunc(numerator, denominator) * denominator == numerator`.
fn divExact(numerator: T, denominator: T) T {}

/// Performs floored division. Rounds toward negative infinity. For unsigned integers it is the same as `numerator / denominator`.
/// Caller guarantees `denominator != 0` and `!(@typeInfo(T) == .Int and T.is_signed and numerator == std.math.minInt(T) and denominator == -1)`.
fn divFloor(numerator: T, denominator: T) T {}

/// Performs truncated division. Rounds toward zero. For unsigned integers it is the same as `numerator / denominator`.
/// Caller guarantees `denominator != 0` and `!(@typeInfo(T) == .Int and T.is_signed and numerator == std.math.minInt(T) and denominator == -1)`.
fn divTrunc(numerator: T, denominator: T) T {}

/// Returns a compile time constant pointer to null-terminated, fixed-size array with length equal to the byte count of
/// the file given by `path`. The contents of the array are the contents of the file. This is equivalent to a string
/// literal with the file contents.
fn embedFile(comptime path: []const u8) *const [N:0]u8 {}
