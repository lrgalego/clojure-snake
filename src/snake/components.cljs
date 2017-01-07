(ns snake.components)

(defn snake-body-part
  [body-part part-type]
  (let [x (* 30 (:x body-part))
        y (* 30 (:y body-part))
        className (str "snake-" part-type)]
    [:div {:key (str part-type "-snake-X" x "Y" y)
           :className (str "snake-part " className)
           :style {:left x :top y}}]))

(defn snake [snake]
  (let [body (:body snake)
        dead (:dead snake)]
  (cons (snake-body-part (first body) (if dead "dead" "head"))
        (map #(snake-body-part % "body") (rest body)))))

(defn fruit [{type :type x :x y :y} f]
  [:div {:key (str "fruit-X" x "Y" y)
         :className (str "fruit fruit-" type)
         :style {:left (* 30 x) :top (* 30 y)}}])

(defn fruits [fruits]
  (map fruit fruits))

(defn wall [{x :x y :y} wall]
  [:div {:key (str "wall-X" x "Y" y)
         :className "wall"
         :style {:left (* 30 x) :top (* 30 y)}}])

(defn walls [walls]
  (map wall walls))

(defn restart [snake]
  (when (:dead snake)
        [:div {:className "death-cover"}
              [:a {:href "#"} "restart"]]))

(defn game [world]
  [:div [:div (fruits (:fruits world))]
        [:div (walls (:walls world))]
        [:div (snake (:snake world))]
        (restart (:snake world))])
