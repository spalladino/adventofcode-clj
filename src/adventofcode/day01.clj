(ns adventofcode.day01)

(defn value [paren]
  (case paren
    \( 1
    \) -1
    0))

(defn floor [parens]
  (reduce +
    (map value parens)))

(defn basement [parens]
  (->> parens
       (map value)
       (reductions +)
       (take-while #(>= % 0))
       (count)
       (inc)))

(defn main []
  (let [data (slurp "./inputs/day01.txt")]
    (println "Santa is in floor:   " (floor data))
    (println "Basement reached on: " (basement data))))
