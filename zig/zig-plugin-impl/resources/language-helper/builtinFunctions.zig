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
