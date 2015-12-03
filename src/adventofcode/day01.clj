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
  (+ 1 (count
    (take-while #(>= % 0)
      (reductions +
        (map value parens))))))

(defn main []
  (let [data (slurp "./inputs/day01.txt")]
    (println "Santa is in floor: " (floor data))
    (println "Basement reached on index: " (basement data))))
