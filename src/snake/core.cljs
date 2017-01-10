(ns snake.core
  (:require [reagent.core :as reagent :refer [atom]]
            [snake.keyboard :refer [setup-keyboard!]]
            [snake.components :refer [game!]]
            [snake.world :refer [refresh!
                                 default-world]]))

(enable-console-print!)

(defonce world
  (atom default-world))

(reagent/render-component
  [#(game! world)]
  (. js/document (getElementById "app")))

(setup-keyboard! world)

(defonce refresh-game!
  (js/setInterval #(refresh! world) 10))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
