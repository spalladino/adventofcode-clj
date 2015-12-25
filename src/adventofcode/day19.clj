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

(defn parse-lines [[molecule & expansions]]
  {:molecule molecule
   :expansions (map parse-line expansions)})

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

(defn calibration [{molecule :molecule expansions :expansions}]
  (->> expansions
    (mapcat #(replacements % molecule))
    (distinct)
    (count)
    (dec)))

(def expand-molecule
  (memoize (fn [expansions molecule]
    (distinct (mapcat #(replacements % molecule) expansions)))))

(defn generate-molecules-bfs [expansions molecules target]
  (let [next-gen (->> molecules
                   (mapcat (partial expand-molecule expansions))
                   (distinct)
                   (remove (set molecules)))]
    (if (some #(= % target) next-gen) 1
        (inc (generate-molecules-bfs expansions next-gen target)))))

(defn deconstruct-molecule-heur [expansions molecule]
  (if-let [[lft rgt] (first (filter #(.contains molecule (last %)) expansions))]
    (let [match-count (count (re-seq (re-pattern rgt) molecule))
          replaced (str/replace molecule rgt lft)
          [goal iters] (deconstruct-molecule-heur expansions replaced)]
      (list goal (+ match-count iters)))
    (list molecule 0)))

(defn deconstruct-molecule-dfs [expansions molecule]
  (if (= molecule "e")
    [0]
    (->> expansions
      (filter #(.contains molecule (last %)))
      (map (fn [[lft rgt]]
             (let [match-count (count (re-seq (re-pattern rgt) molecule))
                  replaced (str/replace molecule rgt lft)
                  deconstructions (deconstruct-molecule-dfs expansions replaced)]
                (if-let [iters (first (remove nil? deconstructions))]
                  (+ match-count iters)
                  nil)))))))

(defn generation-bfs [{molecule :molecule expansions :expansions}]
  (generate-molecules-bfs expansions ["e"] molecule))

(defn generation-heur [{molecule :molecule expansions :expansions}]
  (deconstruct-molecule-heur
    (sort-by (comp count last) > expansions)
    molecule))

(defn generation-dfs [{molecule :molecule expansions :expansions}]
  (first
    (deconstruct-molecule-dfs
      (sort-by (comp count last) > expansions)
      molecule)))

(defn main []
  (let [data (parse-data (slurp "./inputs/day19.txt"))]
    (println "Distinct molecules:   "    (calibration data))
    (println "Minimum replacements to: " (generation-dfs data))))
