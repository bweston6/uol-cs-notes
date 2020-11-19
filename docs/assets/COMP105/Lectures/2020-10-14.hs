addTwo x = x + 2
addSquares x y = x**2 + y**2
maxThree x y z = max (max x y) z
pow2 x = 2^x -- raises input as a power of 2
bin_to_dec b1 b2 = b1 * pow2 1 + b2 * pow2 0 
{- uses the previous function to convert a two bit binary number to
 - a decimal -}
