(ns snake.core
  (:require [reagent.core :as reagent :refer [atom]]
            [snake.keyboard :refer [setup-keyboard]]
            [snake.snake :as s]
            [snake.components :refer [game]]
            [snake.world :as w]))

(enable-console-print!)

(defonce world
  (atom w/default-world))

(defn refresh-world []
  (let [snake (:snake @world)]
    (swap! world w/move-snake)))

(defn turn-right []
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-right snake))
    (refresh-world)))

(defn turn-left []
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-left snake))
    (refresh-world)))

(defn turn-up []
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-up snake))
    (refresh-world)))

(defn turn-down []
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-down snake))
    (refresh-world)))

(reagent/render-component
  [#(game @world)]
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
