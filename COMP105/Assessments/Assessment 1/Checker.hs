-- The Assignment 1 checker.
--
-- Please read the instructions in the handout carefully before running this
-- checker.
--
-- Note that you *MUST* change the directory before loading this file in, or
-- ghci will not be able to find Assignment1.hs. The instructions in the handout
-- tell you how to do this.


{-# LANGUAGE ExistentialQuantification #-}

import System.Timeout
import Control.Exception
import Data.Maybe

import Assignment1


---- Building test cases

data Test = forall a b. (Show b, Eq b) => Test
    {
        function :: a -> b,
        argument :: a,
        expected :: b,
        display  :: String
    }

oneArg :: (Show a, Show b, Eq b) => (a -> b) -> a -> b -> String -> Test
oneArg func arg exp name = Test 
    {
        function = func,
        argument = arg,
        expected = exp,
        display  = unwords [name, show arg]
    }

twoArg :: (Show a, Show b, Show c, Eq c) => 
            (a -> b -> c) -> a -> b -> c -> String -> Test
twoArg func arg1 arg2 exp name = Test 
    {
        function = uncurry func,
        argument = (arg1, arg2),
        expected = exp,
        display  = unwords [name, show arg1, show arg2]
    }


---- Running test cases

data Result = Terminated { output  :: String, correct :: Bool }
            | Exception  { message :: String }
            | Timeout


runFunc :: Test -> Result
runFunc (Test func arg exp disp) = 
        Terminated { output = show out, correct = out == exp}
    where out = func arg

handler :: SomeException -> IO Result
handler e = return $ case show e of "<<timeout>>" -> Timeout
                                    _             -> Exception $ takeWhile (/='\n') $ show e

runTest :: Test -> IO Result
runTest test = do
    let tester = do
        let out = runFunc test
        evaluate $ correct out -- this will force all exceptions
        return out
    res <- timeout 1000000 $ tester `catch` handler
    return $ fromMaybe Timeout res


---- Displaying results

expectedOutput :: Test -> String
expectedOutput (Test _ _ exp _) = show exp

showResult :: (Test, Result) -> String
showResult (test, result) = 
    let
        called = "called: " ++ display test
        expected = "expected: " ++ expectedOutput test
        actual = "output:   " ++ case result of 
            (Terminated out correct) -> 
                out ++ " (" ++ (if correct then "correct" else "WRONG") ++ ")"
            (Exception msg) -> "Exception (" ++ msg ++ ")"
            Timeout -> "Timeout"
    in
        called ++ "\n    " ++  expected ++ "\n    " ++ actual ++ "\n"

passed :: Result -> Bool
passed (Terminated _ c) = c
passed (Exception _ )   = False
passed Timeout          = False

passedStats :: [Result] -> String
passedStats results = "passed: " ++ pass ++ ", failed: " ++ fail
    where passCount = length $ filter passed results
          pass = show passCount
          fail = show $ length results - passCount

compileCheck :: IO ()
compileCheck = 
    putStrLn "Your Assignment1 file compiles successfully and has correct types"


test :: IO ()
test = do
    results <- mapM runTest testCases
    mapM_ (putStrLn . showResult) $ zip testCases results 
    putStrLn $ passedStats results
    putStrLn "\nNOTE: Passing these tests does not guarantee any marks."
    putStrLn "      You are advised to test thoroughly with your own inputs."


---- The test cases

testCases :: [Test]
testCases = [
    oneArg Assignment1.char_to_int    '1'              (1::Integer) "char_to_int",
    twoArg Assignment1.repeat_char    'a' (3::Integer) "aaa"        "repeat_char",
    oneArg Assignment1.decode         "a1b2"           "abb"        "decode",
    oneArg Assignment1.int_to_char    (1::Integer)     '1'          "int_to_char",
    twoArg Assignment1.length_char    'a' "aaab"       (3::Integer) "length_char",
    twoArg Assignment1.drop_char      'a' "aaab"       "b"          "drop_char",
    oneArg Assignment1.encode         "aaabbbccc"      "a3b3c3"     "encode",
    oneArg Assignment1.complex_encode "aaabbbccc"      "a3b3c3"     "complex_encode",
    oneArg Assignment1.complex_decode "a1b2"           "abb"        "complex_decode"
    ]

