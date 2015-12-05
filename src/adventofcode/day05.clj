(ns adventofcode.day05
  (:require [clojure.string :as str]))

(defn is-nice-string [str]
  (and
    (<= 3 (count (re-seq #"[aeiou]" str)))
    (re-find #"([a-z])\1" str)
    (not-any? #(re-find % str) [#"ab" #"cd" #"pq" #"xy"])))

(defn is-nice-string-v2 [str]
  (and
    (re-find #"([a-z][a-z])[a-z]*\1" str)
    (re-find #"([a-z])[a-z]\1" str)))

(defn nice-strings-count [pred data]
  (count (filter pred data)))

(defn main []
  (let [data (str/split-lines (slurp "./inputs/day05.txt"))]
    (println "Count of nice strings part 1: " (nice-strings-count is-nice-string data))
    (println "Count of nice strings part 2: " (nice-strings-count is-nice-string-v2 data))))
