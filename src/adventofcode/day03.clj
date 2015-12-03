(ns adventofcode.day03)

(defn value [dir]
  (case dir
    \^ '(1 0)
    \> '(0 1)
    \v '(-1 0)
    \< '(0 -1)
    '(0 0)))

(defn move [pos dir]
  (map + pos dir))

(defn move-both [positions dir]
  (let [[pos1 pos2] positions]
    [pos2 (move pos1 dir)]))

(defn robo-houses [dirs]
  (let [init-positions ['(0 0) '(0 0)]]
    (->> dirs
         (map value)
         (reductions move-both init-positions)
         (apply concat)
         (distinct)
         (count))))

(defn uniq-houses [dirs]
  (->> dirs
       (map value)
       (reductions move)
       (distinct)
       (count)))

(defn main []
  (let [data (slurp "./inputs/day03.txt")]
    (println "Houses served:   " (uniq-houses data))
    (println "With robo-santa: " (robo-houses data))))
