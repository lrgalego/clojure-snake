(ns snake.snake)

(defn move-left
  [{x :x y :y} head-position]
  {:x (dec x) :y y})

(defn turn-left [snake]
  (assoc snake :direction move-left))

(defn move-right
  [{x :x y :y} head-position]
  {:x (inc x) :y y})

(defn turn-right [snake]
  (assoc snake :direction move-right))

(defn move-up
  [{x :x y :y} head-position]
  {:x x :y (dec y)})

(defn turn-up [snake]
  (assoc snake :direction move-up))

(defn move-down
  [{x :x y :y} head-position]
  {:x x :y (inc y)})

(defn turn-down [snake]
  (assoc snake :direction move-down))

(defn eating-fruit [snake fruits]
  (let [{x :x y :y} (first (:body snake))]
    (first (filter #(and (= x (:x %))
                         (= y (:y %)))
                   fruits))))

(defn headless-body [snake-body is-eating]
  (if is-eating snake-body (drop-last snake-body)))

(defn move-snake [snake fruits]
  (let [body (:body snake)
        move (:direction snake)
        is-eating (eating-fruit snake fruits)
        new-body (cons (move (first body))
                       (headless-body body is-eating))]
    (assoc snake :body new-body)))

(def default-snake
  {:direction move-left
   :body [{:x 5 :y 5}
          {:x 5 :y 6}
          {:x 5 :y 7}
          {:x 5 :y 8}]})

