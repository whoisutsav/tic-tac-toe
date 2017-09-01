(ns tic-tac-toe.player-setup
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.validation :as validation]))

(def default-markers [:O :X])

(defn- invalid-marker [error-str]
  (console-ui/print-message error-str)
  (console-ui/get-user-input))

(defn- human-marker-loop [taken-marker]
  (loop [marker (console-ui/get-user-input)]
    (let [error-str (:error (validation/marker marker taken-marker))]
      (if (nil? error-str)
        (keyword marker)
        (recur (invalid-marker error-str))))))

(defmulti get-marker (fn [player-type taken-marker] player-type))

(defmethod get-marker :human [_ taken-marker]
  (console-ui/print-marker-prompt taken-marker)
  (human-marker-loop taken-marker))

(defmethod get-marker :default [_ taken-marker]
  (let [marker (-> (remove #(= taken-marker %) default-markers)
                   (first))]
    (console-ui/print-computer-marker marker)
    marker))

(defn make-player [player-type marker]
  {:type player-type :marker marker})

(defn setup-new 
  ([player-type] (setup-new player-type nil))
  ([player-type taken-marker] 
   (->> (get-marker player-type taken-marker)
        (make-player player-type))))

