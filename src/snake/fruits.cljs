(ns snake.fruits)

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

