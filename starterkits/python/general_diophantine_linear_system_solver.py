import json
import sys

import numpy as numpy
from smithnormalform import matrix, snfproblem, z


def arbitrary_matrix2d_to_numpy_array(matrix2d):
    array = list()
    for i in range(matrix2d.h):
        array.append(list())
        for j in range(matrix2d.w):
            array[i].append(round(matrix2d.get(i, j).a))
    return numpy.array(array)


# for reference:
# https://en.wikipedia.org/wiki/Diophantine_equation#System_of_linear_Diophantine_equations
# https://pypi.org/project/smithnormalform/
def main(args):
    if len(args) != 2:
        print("")
        return
    # parsing args
    A_matrix = numpy.asarray(json.loads(args[0]))
    A_map = map(lambda x: z.Z(round(x)), A_matrix.flat)
    A_z = list(A_map)
    C_matrix = numpy.asarray(json.loads(args[1]))
    # finding the smith normal form
    problem = snfproblem.SNFProblem(matrix.Matrix(A_matrix.shape[0], A_matrix.shape[1], A_z))
    problem.computeSNF()
    if not problem.isValid():
        print("")
        return
    B = arbitrary_matrix2d_to_numpy_array(problem.J)
    U = arbitrary_matrix2d_to_numpy_array(problem.S)
    D = U.dot(C_matrix)
    V = arbitrary_matrix2d_to_numpy_array(problem.T)

    # finalizing the problem
    V_1X_as_list = list()
    smallest_size = min(B.shape[0], B.shape[1])
    for i in range(smallest_size):
        V_1X_as_list.append(D[i]//B[i][i])

    remaining_amount_of_elements_to_add = B.shape[1] - smallest_size
    for i in range(remaining_amount_of_elements_to_add):
        V_1X_as_list.append(0)
    V_1X = V_1X_as_list
    X = V.dot(V_1X)

    if X.shape[0] == 0:
        print("")
        return

    print("B:")
    print(B)
    print("U:")
    print(U)
    print("V:")
    print(V)

    print("X:")
    print(X)
    X = list(map(lambda x: round(x), X))

    # formatting the output
    output = str(X[0])
    for i in range(1, len(X)):
        output += ":" + str(X[i])

    print(output)

if __name__ == "__main__":
    main(sys.argv[1:])
