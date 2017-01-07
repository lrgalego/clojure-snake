(ns snake.walls)

(defn build-walls [specs]
  (set (mapcat
         (fn [spec]
           (cond
             (:xrange spec)
             (map #(hash-map :x % :y (:y spec))
                  (apply range (:xrange spec)))
             (:yrange spec)
             (map #(hash-map :x (:x spec) :y %)
                  (apply range (:yrange spec)))
             :default [spec]))
         specs)))

(def default-wall (build-walls [{:xrange '(0 16) :y 0}
                                {:xrange '(0 16) :y 15}
                                {:xrange '(3 13) :y 5}
                                {:xrange '(3 13) :y 10}
                                {:yrange '(0 16) :x 0}
                                {:yrange '(0 16) :x 15}]))

