#!/bin/bash
rm -rf docs/COMP*/*/_posts/*
rm -rf docs/assets/COMP*/*/*


cp -r COMP105/Lectures/*/*.md docs/COMP105/Lectures/_posts
cp -r COMP105/Lectures/*/* docs/assets/COMP105/Lectures
rm -rf docs/assets/COMP105/Lectures/*.md


cp -r COMP107/Lectures/*/*.md docs/COMP107/Lectures/_posts
cp -r COMP107/Lectures/*/* docs/assets/COMP107/Lectures
rm -rf docs/assets/COMP107/Lectures/*.md

cp -r COMP107/Seminars/*/*.md docs/COMP107/Seminars/_posts
cp -r COMP107/Seminars/*/* docs/assets/COMP107/Seminars
rm -rf docs/assets/COMP107/Seminars/*.md

cp -r COMP107/Tutorials/*/*.md docs/COMP107/Tutorials/_posts
cp -r COMP107/Tutorials/*/* docs/assets/COMP107/Tutorials
rm -rf docs/assets/COMP107/Tutorials/*.md


cp -r COMP109/Lectures/*/*.md docs/COMP109/Lectures/_posts
cp -r COMP109/Lectures/*/* docs/assets/COMP109/Lectures
rm -rf docs/assets/COMP109/Lectures/*.md

cp -r COMP109/Catch-up\ Sessions/*/*.md docs/COMP109/Catch-up\ Sessions/_posts
cp -r COMP109/Catch-up\ Sessions/*/* docs/assets/COMP109/Catch-up\ Sessions
rm -rf docs/assets/COMP109/Catch-up\ Sessions/*.md

cp -r COMP109/Tutorials/*/*.md docs/COMP109/Tutorials/_posts
cp -r COMP109/Tutorials/*/* docs/assets/COMP109/Tutorials
rm -rf docs/assets/COMP109/Tutorials/*.md


cp -r COMP111/Lectures/*/*.md docs/COMP111/Lectures/_posts
cp -r COMP111/Lectures/*/* docs/assets/COMP111/Lectures
rm -rf docs/assets/COMP111/Lectures/*.md

cp -r COMP111/Tutorials/*/*.md docs/COMP111/Tutorials/_posts
cp -r COMP111/Tutorials/*/* docs/assets/COMP111/Tutorials
rm -rf docs/assets/COMP111/Tutorials/*.md
