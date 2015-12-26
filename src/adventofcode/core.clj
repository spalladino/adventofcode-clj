(ns adventofcode.core)

(def DAYS (conj (range 20) 24))

(doall
  (map (fn [day] (eval `(require '[~(symbol (format "adventofcode.day%02d" (inc day))) :as ~(symbol (format "day%02d" (inc day)))])))
  DAYS))

(def days
  (apply hash-map
    (flatten
      (map
        (fn [day] [(inc day) (eval (symbol (format "day%02d/main" (inc day))))])
        DAYS))))

(defn -main [arg]
  (let [day (Integer/parseInt arg)]
    (println (str "Day " day))
    ((days day))))
