(ns snake.fruits
  (:require [snake.positions :refer [build-positions]]
            [clojure.set :refer [difference]]))

(def fruit-types ["apple" "cherry" "orange"])

(def sample-fruits [{:type "apple" :x 3 :y 2}
                    {:type "cherry" :x 7 :y 8}
                    {:type "orange" :x 4 :y 4}
                    {:type "cherry" :x 7 :y 6}
                    {:type "apple" :x 2 :y 5}
                    {:type "orange" :x 1 :y 3}])

(def default-fruits [])

(defn remove-fruit [world fruit]
  (if fruit
      (assoc world
             :fruits
             (remove #(= fruit %) (:fruits world)))
      world))

(defn fruit-positions [world]
  (map (fn [{x :x y :y} f] (hash-map :x x :y y))
       (:fruits world)))

(defn valid-fruit-positions [world]
  (let [all-positions (build-positions [{:xrange [0 16]
                                         :yrange [0 16]}])
        walls (:walls world)
        snake (-> world :snake :body)
        fruits (fruit-positions world)]
    (into [] (difference all-positions walls snake fruits))))

(defn add-fruit [world]
  (let [probability (if (= 0 (count (:fruits world))) 60 5)]
    (if (>= probability (rand-int 100))
      (let [positions (valid-fruit-positions world)
            index (rand-int (count positions))
            position (nth positions index)
            type (nth fruit-types (rand-int 3))
            fruits (:fruits world)
            fruit {:type type :x (:x position) :y (:y position)}
            new-fruits (conj fruits fruit)]
        (assoc world :fruits new-fruits))
      world)))

