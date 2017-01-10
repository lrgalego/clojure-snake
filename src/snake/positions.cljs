(ns snake.positions)

(defn clear-map [& args]
  (into {}
        (filter #(identity (second %))
                (apply hash-map args))))

(defn build-positions [specs]
  (set (mapcat
         (fn [spec]
           (cond
             (:xrange spec)
             (build-positions
               (map #(clear-map :x %
                                :y (:y spec)
                                :yrange (:yrange spec))
                    (apply range (:xrange spec))))
             (:yrange spec)
             (map #(hash-map :x (:x spec) :y %)
                  (apply range (:yrange spec)))
             :default [spec]))
         specs)))

