(ns snake.snake)

(defn move-left
  [{x :x y :y} head-position]
  {:x (dec x) :y y})

(defn move-right
  [{x :x y :y} head-position]
  {:x (inc x) :y y})

(defn move-up
  [{x :x y :y} head-position]
  {:x x :y (dec y)})

(defn move-down
  [{x :x y :y} head-position]
  {:x x :y (inc y)})

(defn move-snake [snake]
  (let [body (:body snake)
        move (:direction snake)
        new-body (cons (move (first body))
                       (drop-last body))]
    (assoc snake :body new-body)))

(def default-snake
  {:direction move-left
   :body [{:x 5 :y 5}
          {:x 5 :y 6}
          {:x 5 :y 7}
          {:x 5 :y 8}]})

