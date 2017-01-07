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

(defn headless-body [snake fruits]
  (let [is-eating (eating-fruit snake fruits)
        snake-body (:body snake)]
    (if is-eating snake-body (drop-last snake-body))))

(defn crash [body walls]
  (let [{x :x y :y} (first body)]
    (or
      (not (= (count body) (count (set body))))
      (first (filter #(and (= x (:x %))
                           (= y (:y %)))
                     walls)))))

(defn move-snake [snake fruits walls]
  (let [move (:direction snake)
        head (first (:body snake))
        new-body (cons (move head)
                       (headless-body snake fruits))
        is-dead (crash new-body walls)]
    (assoc snake :body new-body :dead is-dead)))

(def default-snake
  {:direction move-left
   :body [{:x 7 :y 7}
          {:x 8 :y 7}
          {:x 9 :y 7}
          {:x 10, :y 7}]})

