(ns snake.keyboard
  (:require-macros [cljs.core.async.macros :refer [go-loop]])
  (:require [reagent.core :as reagent :refer [atom]]
            [goog.dom :as dom]
            [goog.events :as events]
            [cljs.core.async :refer [put! chan <! >!]]))

(defn event-chan [input]
  (let [out (chan)]
    (go-loop []
      (>! out (<! input))
      (recur))
    out))

(defn event-activity [target event]
  (let [event-broadcast (chan)
       event-listener (event-chan event-broadcast)]
    (events/listen target event #(put! event-broadcast %))
    event-listener))

(defn setup-keyboard [handle-keyboard]
  (let [body (dom/getElement "body")]
    (events/removeAll body "keydown")
    (let [keyboard-activity (event-activity body "keydown")]
      (go-loop []
        (handle-keyboard (<! keyboard-activity))
        (recur)))))
