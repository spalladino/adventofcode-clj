(ns adventofcode.day08
  (:require [clojure.string :as str]))

(defn diff-enc [line]
  (+ 2
    (count (re-seq #"\\|\"" line))))

(defn diff-mem [line]
  (let [mem-chars (re-seq #"\\\\|\\\"|\\x[a-f0-9][a-f0-9]|[a-z0-9]" line)]
    (- (count line) (count mem-chars))))

(defn chars-diff [func data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map func)
    (apply +)))

(defn main []
  (let [data (slurp "./inputs/day08.txt")]
    (println "Chars diff in memory:   " (chars-diff diff-mem data))
    (println "Chars diff in encoding: " (chars-diff diff-enc data))))
