(ns tic-tac-toe.player-setup
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.validation :as validation]))

(def default-computer-markers [:O :X])

(defmulti get-marker (fn [player-type taken-marker] player-type))

(defmethod get-marker :human [player-type taken-marker]
  (console-ui/print-marker-prompt taken-marker)
  (let [marker (console-ui/get-user-input)]
    (if-let [error-str (:error (validation/marker marker taken-marker))] 
      (do (console-ui/print-message error-str) (recur player-type taken-marker)) 
      (keyword marker))))


(defn- default-marker [taken-marker]
  (if (= taken-marker (first default-computer-markers))
    (second default-computer-markers)
    (first default-computer-markers)))

(defmethod get-marker :default [_ taken-marker]
  (let [marker (default-marker taken-marker)]
    (console-ui/print-computer-marker marker)
    marker))

(defn initialize-player 
  ([player-type] (initialize-player player-type nil))
  ([player-type taken-marker] 
   (let [marker (get-marker player-type taken-marker)] 
     {:type player-type :marker marker})))

