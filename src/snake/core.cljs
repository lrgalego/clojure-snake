(ns snake.core
  (:require [reagent.core :as reagent :refer [atom]]
            [snake.keyboard :refer [setup-keyboard]]
            [snake.snake :as s]
            [snake.world :refer [default-world]]))

(enable-console-print!)

(defonce world
  (atom default-world))

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

(defn game []
  [:div (fruits (-> @world :fruits))
        (snake (-> @world :snake :body))])


(defn refresh-snake []
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/move-snake snake))))

(defn turn-right []
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-right snake))
    (refresh-snake)))

(defn turn-left []
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-left snake))
    (refresh-snake)))

(defn turn-up []
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-up snake))
    (refresh-snake)))

(defn turn-down []
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-down snake))
    (refresh-snake)))

(reagent/render-component
  [game]
  (. js/document (getElementById "app")))

(defn handle-keyboard [event]
  (case (.-keyCode event)
    (37 65) (turn-left)
    (39 68) (turn-right)
    (38 87) (turn-up)
    (40 83) (turn-down)
    nil))

(setup-keyboard handle-keyboard)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
