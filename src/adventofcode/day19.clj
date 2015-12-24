(ns adventofcode.day19
  (:require [clojure.string :as str]))

(defn parse-line [line]
  (let [[match lft rgt] (re-find #"(\w+) => (\w+)" line)]
    [lft rgt]))

(defn parse-expansions [lines key-fn val-fn]
  (->> lines
    (map parse-line)
    (group-by key-fn)
    (reduce-kv #(assoc %1 %2 (map val-fn %3)) {})))

(defn parse-lines [[source & expansions]]
  {:source source
   :expansions (map parse-line expansions)})
  ;  :expansions (parse-expansions expansions first last)})

(defn parse-data [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (reverse)
    (parse-lines)))

(defn replacements [[lft rgt] s]
  (let [idx (.indexOf s lft)]
    (if (neg? idx) [s]
      (let [replaced (str/replace-first s lft rgt)
            [head tail] (map (partial apply str) (split-at (+ idx (count lft)) s))]
        (cons replaced
          (map (partial str head) (replacements [lft rgt] tail)))))))

(defn calibration [{source :source expansions :expansions}]
  (dec (count (distinct (mapcat #(replacements % source) expansions)))))

(defn main []
  (let [data (parse-data (slurp "./inputs/day19.txt"))]
    (println "Distinct molecules: " (calibration data))))
