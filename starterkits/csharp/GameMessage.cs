using System.Collections.Generic;
using System;

using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Blitz2022
{
    public class Question
    {
        public IList<TotemQuestion> totems;
    }

    public class TotemQuestion
    {
        public Totem shape;
    }

    [JsonConverter(typeof(StringEnumConverter))]
    public enum Totem
    {
        I, O, J, L, S, Z, T
    }

    public class Answer
    {
        public IList<TotemAnswer> totems;
    }

    public class TotemAnswer
    {
        public Totem shape;
        public IList<CoordinatePair> coordinates;
    }

    [JsonConverter(typeof(CoordinatePairJsonConverter))]
    public class CoordinatePair : Tuple<int, int>
    {
        public CoordinatePair(int x, int y)
        : base(x, y)
        { }
    }

    public class CoordinatePairJsonConverter : JsonConverter<CoordinatePair>
    {
        public override void WriteJson(JsonWriter writer, CoordinatePair value, JsonSerializer serializer)
        {
            writer.WriteStartArray();
            writer.WriteValue(value.Item1);
            writer.WriteValue(value.Item2);
            writer.WriteEndArray();
        }

        public override CoordinatePair ReadJson(JsonReader reader, Type objectType, CoordinatePair existingValue, bool hasExistingValue, JsonSerializer serializer)
        {
            throw new NotImplementedException();
        }
    }

    public static class CoordinatePairListExtensions
    {
        public static void Add(this IList<CoordinatePair> list,
            (int, int) item) => list.Add(new CoordinatePair(item.Item1, item.Item2));
    }

    public class GameMessage
    {
        public int tick;

        public Question payload;
    }
}