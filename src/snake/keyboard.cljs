(ns snake.keyboard
  (:require [snake.world :refer [refresh-world!
                                 reset-world!
                                 refresh-game!]]
            [snake.snake :as s]
            [goog.dom :as dom]
            [goog.events :as events]))

(defn turn! [world turn]
  (let [snake (:snake @world)
        refresh-game? (:refresh-game @world)]
    (swap! world assoc :snake (turn snake) :refresh-game false)
    (refresh-world! world)
    (when refresh-game? (refresh-game! world))))

(defn handle-keyboard [world event]
  (let [code (.-keyCode event)]
    (if (-> @world :snake :dead)
      (case code
        (13 32) (reset-world! world)
        nil)
      (case code
        (37 65) (turn! world s/turn-left)
        (39 68) (turn! world s/turn-right)
        (38 87) (turn! world s/turn-up)
        (40 83) (turn! world s/turn-down)
        nil))))

(defn setup-keyboard! [world]
  (let [body (dom/getElement "body")]
    (events/removeAll body "keydown")
    (events/listen body "keydown" #(handle-keyboard world %))))
