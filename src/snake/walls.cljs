(ns snake.walls
  (:require [snake.positions :refer [build-positions]]))

(def default-wall (build-positions [{:xrange '(0 16) :y 0}
                                    {:xrange '(0 16) :y 15}
                                    {:xrange '(3 13) :y 5}
                                    {:xrange '(3 13) :y 10}
                                    {:yrange '(0 16) :x 0}
                                    {:yrange '(0 16) :x 15}]))

