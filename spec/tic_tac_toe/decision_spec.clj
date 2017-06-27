(ns tic-tac-toe.decision-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.decision :refer :all]))

(describe "no-more-moves?"
          (it "returns true if game is over"
              (should= true (no-more-moves? [:o :x :o
                                    :o :x :x
                                    :o :o :x ])))
          (it "returns false is game is not over"
              (should= false (no-more-moves? [:x :o :_
                                     :_ :_ :_
                                     :_ :_ :_ ]))))

(describe "winner"
          (it "returns nil for no winner"
              (should= nil (winner [:_ :_ :_
                                    :_ :_ :_
                                    :_ :_ :_])))
          (it "returns player for winning row"
              (should= :x (winner [:x :x :x
                                   :_ :o :o
                                   :_ :_ :_ ])))
          (it "returns player for winning column"
              (should= :o (winner [:o :x :_
                                   :o :x :_
                                   :o :_ :x])))
          (it "returns player for winning diagonal"
              (should= :o (winner [:o :x :_
                                   :x :o :_
                                   :x :_ :o]))))
