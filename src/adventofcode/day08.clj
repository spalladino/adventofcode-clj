(ns adventofcode.day08
  (:require [clojure.string :as str]))

(defn chars-diff-line [line]
  (let [mem-chars (re-seq #"\\\\|\\\"|\\x[a-f0-9][a-f0-9]|[a-z0-9]" line)]
    (- (count line) (count mem-chars))))

(defn chars-diff [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map chars-diff-line)
    (apply +)))

(defn main []
  (let [data (slurp "./inputs/day08.txt")]
    (println "Chars for part1: " (chars-diff data))))
