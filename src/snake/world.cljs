(ns snake.world
  (:require [snake.snake :as s]
            [snake.fruits :refer [default-fruits
                                  sample-fruits
                                  remove-fruit]]
            [snake.walls :refer [default-wall]]))

(def default-world
  {:refresh-at 0
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

(defn refresh! [world]
  (let [w @world
        now (.now js/Date)
        refresh? (> now (:refresh-at w))
        alive? (not (-> w :snake :dead))]
    (when (and alive? refresh?)
      (do
        (refresh-world! world)
        (swap! world assoc :refresh-at (+ 500 now))))))

(defn reset-world! [world]
  (reset! world default-world))
