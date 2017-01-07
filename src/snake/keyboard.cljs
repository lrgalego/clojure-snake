(ns snake.keyboard
  (:require [snake.world :refer [refresh-world! reset-world!]]
            [snake.snake :as s]
            [goog.dom :as dom]
            [goog.events :as events]))

(defn turn-right! [world]
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-right snake))
    (refresh-world! world)))

(defn turn-left! [world]
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-left snake))
    (refresh-world! world)))

(defn turn-up! [world]
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-up snake))
    (refresh-world! world)))

(defn turn-down! [world]
  (let [snake (:snake @world)]
    (swap! world assoc :snake (s/turn-down snake))
    (refresh-world! world)))

(defn handle-keyboard [world event]
  (let [code (.-keyCode event)]
    (if (-> @world :snake :dead)
      (case code (13 32) (reset-world! world))
      (case code
        (37 65) (turn-left! world)
        (39 68) (turn-right! world)
        (38 87) (turn-up! world)
        (40 83) (turn-down! world)
        nil))))

(defn setup-keyboard! [world]
  (let [body (dom/getElement "body")]
    (events/removeAll body "keydown")
    (events/listen body "keydown" #(handle-keyboard world %))))
