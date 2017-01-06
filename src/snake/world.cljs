(ns snake.world
  (:require [snake.snake :refer[default-snake]]))

(def default-world
  {:snake default-snake
   :fruits [{:type "apple" :x 3 :y 2}
            {:type "cherry" :x 7 :y 8}]})
