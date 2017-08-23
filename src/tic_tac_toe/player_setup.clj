(ns tic-tac-toe.player-setup
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.validation :as validation]))

(def default-computer-markers [:O :X])

;TODO get rid of
(declare human-marker-loop)

(defn- invalid-marker [error-str taken-marker]
  (console-ui/print-message error-str)
  (human-marker-loop taken-marker))

(defn- human-marker-loop [taken-marker]
  (let [marker (console-ui/get-user-input)]
    (if-let [error-str (:error (validation/marker marker taken-marker))] 
      (invalid-marker error-str taken-marker) 
      (keyword marker))))

(defn- default-marker [taken-marker]
  (if (= taken-marker (first default-computer-markers))
    (second default-computer-markers)
    (first default-computer-markers)))

(defmulti get-marker (fn [player-type taken-marker] player-type))

(defmethod get-marker :human [player-type taken-marker]
  (console-ui/print-marker-prompt taken-marker)
  (human-marker-loop taken-marker))

(defmethod get-marker :default [_ taken-marker]
  (let [marker (default-marker taken-marker)]
    (console-ui/print-computer-marker (name marker))
    marker))

(defn- create-player 
  ([player-type] (create-player player-type nil))
  ([player-type taken-marker] 
   (let [marker (get-marker player-type taken-marker)] 
     {:type player-type :marker marker})))

(defn- get-opponent [game-type human-player]
  (let [human-marker (:marker human-player)] 
    (case game-type
      :human-vs-human (create-player :human human-marker)
      :human-vs-computer (create-player :computer human-marker)
      :human-vs-hard-computer (create-player :hard-computer human-marker))))

(defn initialize-players [game-type]
  (let [human-player (create-player :human)
        opponent-player (get-opponent game-type human-player)] 
    [human-player opponent-player]))
