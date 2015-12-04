(ns adventofcode.day04
  (:require [digest :as dig]))

(defn hash-input-match [data match]
  (->> (range)
       (map (partial str data))
       (map dig/md5)
       (take-while (complement #(.startsWith % match)))
       (count)))

(defn main []
  (let [data "iwrupvqb"]
    (println "Hash input for 5 zeros: " (hash-input-match data "00000"))
    (println "Hash input for 6 zeros: " (hash-input-match data "000000"))))
