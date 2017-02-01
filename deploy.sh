#!/bin/bash
lein clean
lein cljsbuild once min
cd resources/public
cp index.html ~/projects/lrgalego.github.io/clojure-snake/
cp css/style.css ~/projects/lrgalego.github.io/clojure-snake/css/
cp js/compiled/snake.js ~/projects/lrgalego.github.io/clojure-snake/js/compiled/
cd ~/projects/lrgalego.github.io
git add .
git commit -m "deploy clojure-snake"
git push
