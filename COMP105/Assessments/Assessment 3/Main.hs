module Main (get_maze, print_maze, is_wall, place_player, move, can_move, game_loop, get_path, main) where 

import System.Environment
import Control.Monad

maze_path = "/home/ben/Documents/University/COMP105/Assessments/Assessment 3/Resources/maze6.txt"

-- Useful code from Lecture 25
-- You may use this freely in your solutions

get :: [String] -> Int -> Int -> Char
get maze x y = (maze !! y) !! x 

modify_list :: [a] -> Int -> a -> [a]
modify_list list pos new =
    let
        before = take  pos    list
        after  = drop (pos+1) list
    in
        before ++ [new] ++ after

set :: [String] -> Int -> Int -> Char -> [String]
set maze x y char = 
    let
        line = maze !! y
        new_line = modify_list line x char
        new_maze = modify_list maze y new_line
    in
        new_maze

---- Part A

-- Question 1

get_maze :: String -> IO [String]
get_maze file = do
        maze <- readFile file
        return . lines $ maze

-- Question 2

print_maze :: [String] -> IO ()
print_maze maze = putStrLn . unlines $ maze

-- Question 3

is_wall :: [String] -> (Int, Int) -> Bool
is_wall maze (x,y) = '#' == get maze x y 

-- Question 4

place_player :: [String] -> (Int, Int) -> [String]
place_player maze (x,y) = set maze x y '@'

---- Part B

-- Question 5

move :: (Int, Int) -> Char -> (Int, Int)
move (x,y) key
        | key == 'w' = (x,y-1)
        | key == 's' = (x,y+1)
        | key == 'd' = (x+1,y)
        | key == 'a' = (x-1,y)
        | otherwise  = (x,y)

-- Question 6

can_move :: [String] -> (Int, Int) -> Char -> Bool
can_move maze (x,y) key = not . is_wall maze $ move (x,y) key

-- Question 7

game_loop :: [String] -> (Int, Int) -> IO ()
game_loop maze (x,y) = do
        let state = place_player maze (x,y)
        print_maze state
        keyLine <- getLine
        let key = head keyLine
        when (can_move maze (x,y) key) (game_loop maze (move (x,y) key))
        game_loop maze (x,y)

---- Part C

-- Question 8 

get_path :: [String] -> (Int, Int) -> (Int, Int) -> [(Int, Int)]
get_path maze start end = get_path_helper maze start end []

sum_bool_list list = 
        let
                bin_to_int True  = 1
                bin_to_int False = 0
        in
                sum (map bin_to_int list)

get_path_helper :: [String] -> (Int,Int) -> (Int,Int) -> [(Int,Int)] -> [(Int,Int)]
get_path_helper maze (x,y) end path
        -- base cases
        | current == end       = path ++ [current]
        | num_direc_avail == 0 = []
        -- recursive steps
        | num_direc_avail == 3 = [] ++ (go (list_direc_avail !! 0)) ++ (go (list_direc_avail !! 1)) ++ (go (list_direc_avail !! 2))
        | num_direc_avail == 2 = [] ++ (go (list_direc_avail !! 0)) ++ (go (list_direc_avail !! 1))
        | num_direc_avail == 1 = [] ++ (go (list_direc_avail !! 0))
        where   num_direc_avail  = sum_bool_list [(cm 'w'),(cm 'd'),(cm 's'),(cm 'a')]
                go key           = get_path_helper wall_off (move (x,y) key) end (path ++ [current])
                list_direc_avail = (cm_direc 'w') ++ (cm_direc 'd') ++ (cm_direc 's') ++ (cm_direc 'a')
                current     = (x,y)
                wall_off    = set maze x y '#'
                cm key      = can_move maze current key
                cm_direc key
                        | cm key == True = [key]
                        | otherwise      = [] 

-- Question 9

maze_end :: [String] -> (Int,Int)
maze_end maze = (( length . head $ maze) - 2, (length  maze) - 2)

dotted_maze :: [String] -> [(Int, Int)] -> [String]
dotted_maze maze []         = maze 
dotted_maze maze ((x,y):xs) = dotted_maze (set maze x y '.') xs

main :: IO ()
main = do
        path <- getArgs
        maze <- get_maze . head $ path
        print_maze . dotted_maze maze . get_path maze (1,1) $ maze_end maze
