(ns snake.core
  (:require [reagent.core :as reagent :refer [atom]]
            [snake.keyboard :refer [setup-keyboard!]]
            [snake.components :refer [game!]]
            [snake.world :refer [refresh-game! default-world]]))

(enable-console-print!)

(defonce world
  (atom default-world))

(reagent/render-component
  [#(game! world)]
  (. js/document (getElementById "app")))

(setup-keyboard! world)
(refresh-game! world)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
