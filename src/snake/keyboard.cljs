(ns snake.keyboard
  (:require [reagent.core :as reagent :refer [atom]]
            [goog.dom :as dom]
            [goog.events :as events]))

(defn setup-keyboard [handle-keyboard]
  (let [body (dom/getElement "body")]
    (events/removeAll body "keydown")
    (events/listen body "keydown" #(handle-keyboard %))))
