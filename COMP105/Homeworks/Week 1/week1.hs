-- Lecture 2
-- It can be implemented, as this is deterministic and has no side effects.
-- This cannot be implemented as the function will have the side effect of calling the network.
-- No, as the function won't be deterministic as it will give different outputs every time. 
-- Lecture 3
plus_one x = x + 1
five_sum x y = (x + y) * 5
minus_one x = x - 1
quad_power x = 4 ^ x
mod_three x = x `mod` 3
add_three x y z = x + y + z
min_max a b c d  = min a b + max c d
