(ns adventofcode.day16
  (:require [clojure.string :as str])
  (:require [clojure.set]))

(defn parse-line [line]
  (let [[_ id] (re-find #"Sue (\d+):" line)]
    {:id id
     :items (reduce
              (fn [accum [_ item val]]
                (assoc accum item (Integer/parseInt val)))
              {}
              (re-seq  #"(\w+): (\d+)" line))}))

(defn parse-data [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map parse-line)))

(defn match-items [target items]
  (clojure.set/superset? (set target) (set items)))

(defn item-retroencabulator [target [item value]]
  (case item
    ("cats" "trees") (< (target item) value)
    ("pomeranians" "goldfish") (> (target item) value)
    (= (target item) value)))

(defn match-retroencabulator [target items]
  (every?
    (partial item-retroencabulator target)
    (seq items)))

(defn find-sue [data match-func target]
  (->> data
    (parse-data)
    (filter #(match-func target (:items %)))
    (first)
    (:id)))

(defn main []
  (let [data (slurp "./inputs/day16.txt")
        target {"children" 3, "cats" 7, "samoyeds" 2, "pomeranians" 3, "akitas" 0, "vizslas" 0, "goldfish" 5, "trees" 3, "cars" 2, "perfumes" 1}]
    (println "Aunt Sue:              " (find-sue data match-items target))
    (println "With retroencabulator: " (find-sue data match-retroencabulator target))))
