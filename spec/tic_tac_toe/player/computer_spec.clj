(ns tic-tac-toe.player.computer-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.player.computer :refer :all])) 

(describe "get-move"
          (around [it] (with-out-str it))

          (it "returns an empty space"
              (should= 4 (get-move [ :x :o :x
                                     :_ :o :x
                                     :x :x :o ] {:type :computer}))))

