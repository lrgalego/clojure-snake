(ns snake.world
  (:require [snake.snake :as s]))

(defn build-walls [specs]
  (set (mapcat
         (fn [spec]
           (cond
             (:xrange spec)
             (map #(hash-map :x % :y (:y spec))
                  (apply range (:xrange spec)))
             (:yrange spec)
             (map #(hash-map :x (:x spec) :y %)
                  (apply range (:yrange spec)))
             :default [spec]))
         specs)))

(def default-world
  {:snake s/default-snake
   :walls (build-walls [{:xrange '(0 16) :y 0}
                        {:xrange '(0 16) :y 15}
                        {:xrange '(3 13) :y 5}
                        {:xrange '(3 13) :y 10}
                        {:yrange '(0 16) :x 0}
                        {:yrange '(0 16) :x 15}])
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
