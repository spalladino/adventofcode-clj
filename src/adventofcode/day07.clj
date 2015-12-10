(ns adventofcode.day07
  (:require [clojure.string :as str]))

(defn parse-instruction [instruction]
  (let [[match arg-1 operator arg-2 target] (re-find #"([a-z]+|\d+)?\s*([A-Z]+)?\s*([a-z]+|\d+)? -> ([a-z]+)" instruction)
        arg (fn [arg] (if (re-find #"\d+" arg) (Integer/parseInt arg) `(wire-value ~arg)))]
    {:target target
     :operation (case operator
                "NOT" `(bit-xor 65535 ~(arg arg-2))
                "AND" `(bit-and ~(arg arg-1) ~(arg arg-2))
                "OR"  `(bit-or  ~(arg arg-1) ~(arg arg-2))
                "LSHIFT" `(bit-shift-left  ~(arg arg-1) ~(arg arg-2))
                "RSHIFT" `(bit-shift-right ~(arg arg-1) ~(arg arg-2))
                nil (arg arg-1))}))

(declare ^:dynamic the-circuit)
(declare ^:dynamic wire-value)

(defn get-wire-value [key]
  (eval (the-circuit key)))

(defn circuit-value [target circuit]
  (binding [the-circuit circuit
            wire-value (memoize get-wire-value)]
    (wire-value target)))

(defn wire-instruction [circuit instruction]
  (let [{target :target operation :operation} instruction]
    (assoc circuit target operation)))

(defn wire-circuit [instructions]
  (reduce wire-instruction {} instructions))

(defn build-circuit [data]
  (->> data
    (str/split-lines)
    (remove str/blank?)
    (map parse-instruction)
    (wire-circuit)))

(defn solve-circuit [data target]
  (circuit-value target (build-circuit data)))

(defn overriden-circuit [data from to target]
  (let [circuit (build-circuit data)
        value (circuit-value from circuit)
        new-circuit (assoc circuit to value)]
        (circuit-value target new-circuit)))

(defn main []
  (let [data (slurp "./inputs/day07.txt")]
    (println "Value for wire a part1: " (solve-circuit data "a"))
    (println "Value for wire a part2: " (overriden-circuit data "a" "b" "a"))))
