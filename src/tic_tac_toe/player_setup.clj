(ns tic-tac-toe.player-setup
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.validation :as validation]))

(def default-markers [:O :X])

(defmulti get-marker (fn [player-type taken-marker] player-type))

(defmethod get-marker :human [player-type taken-marker]
  (console-ui/print-marker-prompt taken-marker)
  (let [marker (console-ui/get-user-input)
        error-str (:error (validation/marker marker taken-marker))]
    (if (nil? error-str) 
      (keyword marker)
      (do (console-ui/print-message error-str) (recur player-type taken-marker)))))

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

