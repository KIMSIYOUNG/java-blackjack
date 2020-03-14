package domain.result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public enum ResultType {
	WIN("승",  (myScore, otherScore) -> {
		return (!myScore.isBust() && otherScore.isBust()) || myScore.isBiggerThan(otherScore);
	}),
	DRAW("무",  (myScore, otherScore) -> {
		return !myScore.isBust() && !otherScore.isBust() && myScore.isEqualTo(otherScore);
	}),
	LOSE("패",  (myScore, otherScore) -> {
		return myScore.isBust() || myScore.isLowerThan(otherScore);
	});

	private static final Map<ResultType, ResultType> reversed;

	private final String result;
	private final BiFunction<Score, Score, Boolean> expression;

	ResultType(String result, BiFunction<Score, Score, Boolean> expression) {
		this.result = result;
		this.expression = expression;
	}

	static {
		reversed = new HashMap<>();
		reversed.put(WIN, LOSE);
		reversed.put(DRAW, DRAW);
		reversed.put(LOSE, WIN);
	}

	public static ResultType of(Score myScore, Score otherScore) {
		return Arrays.stream(ResultType.values())
			.filter(result -> result.expression.apply(myScore, otherScore))
			.findFirst()
			.get();
	}

	public static ResultType reverse(ResultType resultType) {
		return reversed.get(resultType);
	}

	public String getResult() {
		return result;
	}
}
