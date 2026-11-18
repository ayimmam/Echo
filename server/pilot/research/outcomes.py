OUTCOME_VERSION = "card-usefulness-v1"

def calculate_usefulness(records):
    """Calculate the frozen pilot outcome without treating missing ratings as scores.

    records is an iterable of (participant_key, rating_or_none), one entry per displayed
    review checkpoint. Participant keys are used only for aggregate coverage counts.
    """
    rows = list(records)
    participants = {participant for participant, _ in rows}
    rated_participants = {participant for participant, rating in rows if rating is not None}
    ratings = [rating for _, rating in rows if rating is not None]
    if any(not isinstance(value, int) or isinstance(value, bool) or value not in range(1, 6) for value in ratings):
        raise ValueError("Ratings must be integers from 1 to 5")
    positive = sum(value >= 4 for value in ratings)
    rate = positive / len(ratings) if ratings else None
    return {
        "version": OUTCOME_VERSION,
        "prompted": len(rows),
        "submitted": len(ratings),
        "positive": positive,
        "unrated": len(rows) - len(ratings),
        "distinct_teachers": len(participants),
        "teachers_with_rating": len(rated_participants),
        "teacher_coverage": len(rated_participants) / len(participants) if participants else None,
        "positive_rate": rate,
        "strictly_above_80_percent": rate is not None and rate > 0.8,
    }
