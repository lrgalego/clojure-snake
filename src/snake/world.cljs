(ns snake.world
  (:require [snake.snake :as s]
            [snake.walls :refer [default-wall]]))

(def default-world
  {:snake s/default-snake
   :walls default-wall
   :fruits [{:type "apple" :x 3 :y 2}
            {:type "cherry" :x 7 :y 8}
            {:type "orange" :x 4 :y 4}
            {:type "cherry" :x 7 :y 6}
            {:type "apple" :x 2 :y 5}
            {:type "orange" :x 1 :y 3}]})

(defn remove-eaten-fruit [world fruit]
  (if
    fruit
    (assoc world :fruits (remove #(= fruit %) (:fruits world)))
    world))

(defn move-snake [world]
  (let [snake (:snake world)
        fruits (:fruits world)
        walls (:walls world)
        eaten-fruit (s/eating-fruit snake fruits)
        new-snake (s/move-snake snake fruits walls)
        new-world (remove-eaten-fruit world eaten-fruit)]
    (assoc new-world :snake new-snake)))

(defn refresh-world! [world]
  (let [snake (:snake @world)]
    (swap! world move-snake)))

(defn reset-world! [world]
  (reset! world default-world))
