(ns snake.components)

(defn snake-body-part
  [body-part is-head]
  (let [x (* 30 (:x body-part))
        y (* 30 (:y body-part))
        className (if is-head
                      "snake-head"
                      "snake-body")]
    [:div {:key (str "snake" x "X" y)
           :className (str "snake-part " className)
           :style {:left x :top y}}]))

(defn snake [snake]
  (cons (snake-body-part (first snake) true)
        (map #(snake-body-part % false) (rest snake))))

(defn fruit [{type :type x :x y :y} f]
  [:div {:key (str "fruit" x "X" y)
         :className (str "fruit fruit-" type)
         :style {:left (* 30 x) :top (* 30 y)}}])

(defn fruits [fruits]
  (map fruit fruits))

(defn game [world]
  [:div (fruits (:fruits world))
        (snake (-> world :snake :body))])
