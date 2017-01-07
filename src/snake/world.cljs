(ns snake.world
  (:require [snake.snake :as s]
            [snake.fruits :refer [default-fruits
                                  sample-fruits
                                  remove-fruit]]
            [snake.walls :refer [default-wall]]))

(def default-world
  {:refresh-game true
   :snake s/default-snake
   :walls default-wall
   :fruits sample-fruits})

(defn move-snake [world]
  (let [snake (:snake world)
        fruits (:fruits world)
        walls (:walls world)
        eaten-fruit (s/eating-fruit snake fruits)
        new-snake (s/move-snake snake fruits walls)
        new-world (remove-fruit world eaten-fruit)]
    (assoc new-world :snake new-snake)))

(defn refresh-world! [world]
  (let [snake (:snake @world)]
    (swap! world move-snake)))

(declare refresh-game!)

(defn handle-refresh! [world]
  (when (not (-> @world :snake :dead))
    (if (:refresh-game @world)
      (do
        (refresh-world! world)
        (refresh-game! world))
      (swap! world assoc :refresh-game true))))

(defn refresh-game! [world]
  (js/setTimeout #(handle-refresh! world) 500))

(defn reset-world! [world]
  (reset! world default-world)
  (refresh-game! world))
