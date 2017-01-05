(ns snake.core
  (:require [reagent.core :as reagent :refer [atom]]
            [snake.keyboard :refer [setup-keyboard]]
            [snake.snake :refer [move-left
                                 move-right
                                 move-up
                                 move-down
                                 move-snake
                                 default-snake]]))
(enable-console-print!)

(defn snake-body-part
  [body-part is-head]
  (let [x (* 30 (:x body-part))
        y (* 30 (:y body-part))
        className (if is-head
                      "snake-head"
                      "snake-body")]
    [:div {:key (str x y)
           :className (str "snake-part " className)
           :style {:left x :top y}}]))

(defonce snake-state
  (atom default-snake))

(defn snake []
  (let [snake (:body @snake-state)]
    (cons (snake-body-part (first snake) true)
          (map #(snake-body-part % false) (rest snake)))))

(defn refresh-snake []
  (reset! snake-state (move-snake @snake-state)))

(defn turn-right []
  (swap! snake-state assoc :direction move-right)
  (refresh-snake))

(defn turn-left []
  (swap! snake-state assoc :direction move-left)
  (refresh-snake))

(defn turn-up []
  (swap! snake-state assoc :direction move-up)
  (refresh-snake))

(defn turn-down []
  (swap! snake-state assoc :direction move-down)
  (refresh-snake))

(defn game []
  [:div (snake)])

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
